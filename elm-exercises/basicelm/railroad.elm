import Html exposing (..)
import Html.Events exposing (..)

--
-- Railroad exercise
-- The application below implements a railroad which does the following
-- 1: Takes a list of Strings and merges it into one string
-- 2: Counts the size of the string
-- 3: Transforms that size into a Model
-- Your job is to implement the three functions
--

type alias Model
  = { characters : Maybe Int }

type Msg
  = Count (Maybe (List String))

view : Model -> Html Msg
view model =
  div []
  [ text (toString model.characters)
  , br [] []
  , button [ onClick (Count (Nothing))] [text "Count Nothing (Nothing)"]
  , button [ onClick (Count (Just [""]))] [text "Count [] (Just 0)"]
  , button [ onClick (Count (Just ["one", "two"]))] [ text "Count [one, two] (Just 6)" ]
  , button [ onClick (Count (Just [" ", "", ""]))] [text "Count [' ', '', ''] (Just 1)"]
  , button [ onClick (Count (Just ["To", "be", "or", "not", "to", "be"]))] [text "Count [to, be, or, not, to, be] (Just 13)"]
  ]

update : Msg -> Model -> Model
update msg model =
  case msg of
    Count maybeOfListMaybe -> maybeOfListMaybe
      |> mergeStrings
      |> countStringLength
      |> resultToModel 

main : Program Never Model Msg
main = Html.beginnerProgram
  { model = Model Nothing
  , view = view
  , update = update }

--
-- Implement the three functions below
--

mergeStrings : Maybe (List String) -> Result String String
mergeStrings maybe =
  case maybe of
    Nothing -> Err "This list doesn't exist"
    Just list -> Ok (String.join "" list)

countStringLength : Result String String -> Result String Int
countStringLength result =
  case result of
    Err string -> Err "This string doesn't exist"
    Ok string -> Ok (String.length string)

resultToModel : Result String Int -> Model
resultToModel result =
  case result of
    Err string -> Model Nothing
    Ok int -> Model (Just int)
