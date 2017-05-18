module Main where

println :: String -> String
println a = a
--import Lib

factorial :: Integer -> Integer
factorial 0 = 1
factorial a = a * factorial(a - 1)
--factorial a =
--  if a == 0 then 1 else a * factorial(a - 1)

revLength :: String -> Int
--revLength input = length $ reverse input
--revLength input = (length . reverse) input
revLength = length . reverse

main :: IO ()
--main = putStrLn "hi" >> putStrLn "there"

--main = do
--  putStrLn "factorial of 5: "
--  putStrLn $ show $ factorial 5

--main = do
--  (putStrLn . show . factorial) 5

main = do
  putStrLn $ show $ revLength "hello"
