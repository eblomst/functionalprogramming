import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (..)
import Http
import Json.Decode as Decode

main =
    Html.program
        {
            init = (Model 0, Cmd.none)
            , view = view
            , update = update
            , subscriptions = \x -> Sub.none 
        }


type Msg 
    = Counter (Result Http.Error Int)
    | Get
    | Put


type alias Model 
    = { counter : Int }

view : Model -> Html Msg
view model =
    div [] 
    [ h2 [] [ text (toString model.counter) ]
      , button [ onClick Get ] [ text "Get" ]
      , button [ onClick Put ] [ text "Put" ]
    ]
    
decodeUrl : Decode.Decoder Int
decodeUrl = Decode.at ["counter"] Decode.int

getCounter : Cmd Msg
getCounter =
    let
        url = "http://localhost:3000/counter"
      --  request = Http.request
      --      { method = "GET"
      --      , headers =  []
      --      , url = "http://localhost:3000/counter"
      --      , body = Http.emptyBody
      --      , expect = Http.expectJson decodeUrl
      --      , timeout = Nothing
      --      , withCredentials = False
      --}
    in
      Http.send Counter (Http.get url decodeUrl)
      
putCounter : Cmd Msg
putCounter =
    let
      request = Http.request
        { method = "PUT"
        , headers = []
        , url = "http://localhost:3000/counter/1"
        , body = Http.emptyBody
        , expect = Http.expectJson decodeUrl
        , timeout = Nothing
        , withCredentials = False
        }
    in
      Http.send Counter request

update : Msg -> Model -> (Model, Cmd Msg) 
update message model =
    case message of
        Counter (Ok value) -> (Model value, Cmd.none)
        Counter (Err _) -> (model, Cmd.none)
        Get -> (model, getCounter)
        Put -> (model, putCounter)