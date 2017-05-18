port module Poison exposing (..)

import Html exposing (..)
import Html.Attributes exposing (type_, style, name)
import Html.Events exposing (onClick)

--
-- Exercise 2b 1/2
-- In this exercise your job is to:
-- 1. Compile Event.elm to event.js
-- 2. Read and understand the content of Event.elm. In particular
--    figure out which function is ported.
-- 3. Include and render the event.js file in the event.html file
-- 4. Show the current poison in the JavaScript code by using the ported
--    function 'poison' from the JavaScript code in event.html.
--    You should not have to change anything in Event.elm!
--    ... But you have to understand it anyway :-)
--

type alias Model = { poison : Maybe String }

type Msg = SetPoison String

update : Msg -> Model -> (Model, Cmd Msg)
update (SetPoison newPoison) model =
  (Model (Just newPoison), poison (toString newPoison))

port poison : String -> Cmd msg

view : Model -> Html Msg
view model =
  div []
  [ radio "Plague"
  , radio "Cholera"
  , radio "Donald Trump"
  ]

radio : String -> Html Msg
radio poison =
  label []
    [ input [ type_ "radio", name "poison-radio", onClick (SetPoison poison) ] []
    , text poison
    ]

showMessage : String -> Html Msg
showMessage message = h2 [] [ text message ]

main = program
  { update = update
  , view = view
  , init = (Model Nothing, Cmd.none)
  , subscriptions = \model -> Sub.none}
