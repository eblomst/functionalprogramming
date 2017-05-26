import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (..)
import WebSocket
import List
import Json.Decode as Decode
import Json.Encode as Encode

main: Program Never Model Msg
main =
  Html.program
  { init = init
  , view = view
  , update = update
  , subscriptions = subscriptions
  }

 -- MODEL
type alias Model =
  { chatMessage : List String
  , userMessage : String
  , username: String
  }

init : (Model, Cmd Msg)
init =
  ( Model [] "" ""
  , Cmd.none
  )

type alias ChatMessage =
  { command: String
  , content: String
  }

-- UPDATE
type Msg
  = PostChatMessage
  | UpdateUsername String
  | UpdateUserMessage String
  | NewChatMessage String
  | SetUserName

update : Msg -> Model -> (Model, Cmd Msg)
update msg model =
  case msg of
    PostChatMessage ->
      let
        message =
          Encode.object [ ("command", Encode.string "send"), ("content", Encode.string model.userMessage)]
      in
        { model | userMessage = "" } ! [WebSocket.send "ws://localhost:3000/" (Encode.encode 0 message) ]

    UpdateUserMessage message ->
      { model | userMessage = message } ! []

    NewChatMessage message ->
      { model | chatMessage = List.append [msgToString (decodeMessage message)] model.chatMessage} ! []

    SetUserName ->
        let
          username =
            Encode.object [ ("command", Encode.string "login"), ("content", Encode.string model.username)]
        in
          { model | username = "" } ! [WebSocket.send "ws://localhost:3000/" (Encode.encode 0 username)]

    UpdateUsername message ->
      { model | username = message } ! []

        --List.append [message] model.chatMessage } ! []

decodeMessage : String -> Result String String
decodeMessage message = Decode.decodeString (Decode.field "content" Decode.string) message

msgToString : Result String String -> String
msgToString msg =
  case msg of
    Ok msg -> msg
    Err msg -> "Can't decode"

-- VIEW
view : Model -> Html Msg
view model =
  div []
    [ input [ placeholder "username.. "
            , autofocus True
            , value model.username
            , onInput UpdateUsername
            ] []
    , button [ onClick SetUserName ] [text "Login"]
    , input [ placeholder "message..."
            , autofocus True
            , value model.userMessage
            , onInput UpdateUserMessage
            ] []
    , button [ onClick PostChatMessage ] [ text "Submit" ]
    , div [] (List.map viewMessage model.chatMessage)
  ]

viewMessage : String -> Html msg
viewMessage message =
  div [] [ text message ]
 -- SUBSCRIPTIONS
subscriptions : Model -> Sub Msg
subscriptions model =
  WebSocket.listen "ws://localhost:3000" NewChatMessage
