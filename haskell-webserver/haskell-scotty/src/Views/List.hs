{-# LANGUAGE OverloadedStrings #-}
module Views.List where
import Text.Blaze.Html5
import Text.Blaze.Html5.Attributes

  render = do
    html $ do
      body $ do
        h1 "The awesome list"
        hr
        ul $ do
          li "Haskell with WAI"
          li "Haskell with WARP"
          li "Haskell with Scotty"
          li "Haskell with Blaze"
