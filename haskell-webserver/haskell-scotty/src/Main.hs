{-# LANGUAGE OverloadedStrings #-}
import Web.Scotty
import qualified Scotty as S
import qualified Text.Blaze.Html5 as H
import qualified Text.Blaze.Html5.Attributes as A
import Text.Blaze.Html.Renderer.Text

import qualified Views.List as L
blaze = html . renderHtml


main = scotty 3000 $ do
  get "/" $ do
    html "Hello World"

  put "/" $ do
    text "put was called"

  post "/" $ do
    text "posting"

  delete "/" $ do
    text "Are you sure?"

  get "/hello" $ do
    name <- param "name"
    text name

  get "/pet/:name" $ do
    word <- param "name"
    html $ mconcat ["<h1>Scotty's pet is called ", word, "</h1>"]

  get "/list" $ do
    blaze L.render
