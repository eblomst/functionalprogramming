port module Poison exposing (..)

import Html exposing (..)
import Html.Attributes exposing (type_, style, name)
import Html.Events exposing (onClick)

--
-- Exercise 2b 2/2
-- In this exercise your job is to:
-- 1. Compile Subscription.elm to subscription.js
-- 2. Read and understand the content of Subscription.elm
-- 3. Include and render the subscription.js file in the subscription.html file
-- 4. Identify the ported function from Elm and call it from JavaScript with
--    your preferred poison.
-- 5. Now replace your preferred poison with the setPoison JavaScript method,
--    which is used from the radio buttons in subscription.html. In other words
--    Use the radio buttons in the subscription.html file to send the value
--    to Elm.
--

type alias Model = { poison : Maybe String }

type Msg = SetPoison String

update : Msg -> Model -> (Model, Cmd Msg)
update (SetPoison newPoison) model =
  (Model (Just newPoison), Cmd.none)

port injectPoison : (String -> msg) -> Sub msg

view : Model -> Html Msg
view model =
  let
    message = case model.poison of
      Nothing -> "You ain't dead yet mate"
      Just poison -> poison ++ " killed you!"
  in
    div [] [ text message ]

subscriptions : Model -> Sub Msg
subscriptions model =
  injectPoison SetPoison

main = program
  { update = update
  , view = view
  , init = (Model Nothing, Cmd.none)
  , subscriptions = subscriptions}
