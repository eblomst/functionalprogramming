module Main exposing (..)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (..)

--
-- Exercise 1b
-- This exercise is about reusing components with functions instead of objects.
-- Look at the view method. A lot of the code is duplicated there. Can you think
-- of a smarter way to write that?
-- Hint: write a helper-function.
--

-- Model
type alias Model =
  { status : Status }

type Status =
    Single
  | Complicated
  | Relationship
  | Married
  | Other

-- UPDATE
type Msg = SetStatus Status

update : Msg -> Model -> ( Model, Cmd Msg )
update (SetStatus status) model = (Model status, Cmd.none)

view : Model -> Html Msg
view model =
  fieldset []
          [(viewFunc "Single" Other model)
          --(viewFunc "It's complicated" Complicated)
          ]
          --        [ input [ type_ "radio", name "status-radio", checked True, onClick (SetStatus Single) ] []
--        , text "Single"
--        , input [ type_ "radio", name "status-radio", onClick (SetStatus Complicated) ] [text "It's"]
--        , input ]

--        ,
--
--        , text "It's complicated..."
--        ]
--
--        [ input [ type_ "radio", name "status-radio", onClick (SetStatus Relationship) ] []
--        , text "In a relationship"
--        ]
--
--        [ input [ type_ "radio", name "status-radio", onClick (SetStatus Married) ] []
--        , text "Married"
--        ]
--
--        [ input [ type_ "radio", name "status-radio", onClick (SetStatus Other) ] []
--        , text "Keep out of my private life!"
--        ]

viewFunc : String -> Status -> Model -> Html Msg
viewFunc textHtml status isChecked =
  let
    isChecked = status == model.status
    label []
      [ input [ type_ "radio", name "status-radio", checked isChecked, onClick (SetStatus status) ] []
      , text textHtml
      ]

main =
  Html.program
    { init = (Model Single, Cmd.none)
    , view = view
    , update = update
    , subscriptions = \m -> Sub.none
    }
