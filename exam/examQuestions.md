# Exam questions

## 1. Virtual CPU and tail recursion

* What is an instruction?
  An instruction tells 
* What is a CPU?
* What is tail recursion? 
* When can tail recursion be applied?
* Why is tail recursion necessary in FP?

## 2. Map and flatmap

* What is the difference between an array and a linked list?
An array is group of elements that is stored one after another. Linkedlist consists of nodes that has data and a reference to the next node in the list.
Linkedlist has a flexible size and arrays size is final. If you want a larger array you have to create a array that's bigger and then loop through
the previous array and add the values in the new array. If you need to add new data to the linkedlist you just have to create a new node and change the last nodes
reference from null to the new node.
It's faster to get to a specific index in an array because you know exactly where it is (index) but in a linkedlist might not be after each other for example the last node is 
actually at position 6 in memory.
 
* What is recursion?
Recursion is a function that is able to call itself. Some functional programming languages does not define any looping constructs so we use recursion to repeatly to call
code.
(Insert example code)

* What is a higher-order function?

High-order function is a function that takes a function as argument. 
```
 List.map (toSomeThing n) "H, E, J" 
```
* What does mapping mean?

Apply a function to each element in the list.

* What does flattening a list mean?

Flat map is doing two things: it's first taking a list and then flattening
all the lists inside that list into one long list.


## 3. Elm

* What are some of the differences between OOP and FP?


* Why do we need the Elm runtime?
Elm Runtime  -> Update -> View  -> Elm Runtime.
Msg: is the input we get from the client.
Update: receives the Msg and from the Msg updates and goes to the view.
View (HTML): The view consists of HTML and is what we send on the page.
 
* What are some of the benefits of using Elm?
There are no runtime errors. 
* What is the ``Maybe`` type in Elm? When is it used?
We use this type when we what to do Error handling. We have used when doing HTTP requests. 
Maybe is a union type. 
Maybe can return 'Just' or Nothing. The definition looks like this:
```
type Maybe a =
	 Just a
	| Nothing
```
Just returns a genric that can be any type, like String, Integer etc.
Nothing returns nothing. So if there's an error nothing will show. This is maybe not ideel
because the client mostlikely wants to know what went wrong like you didn't enter enough information etc.
Most developers also wants to know what went wrong so they know what to fix. 
```
case msg of
	Just msg -> msg
	Nothing -> 
``` 
* What is the ``Result`` type in Elm? When is it used?
This is used for Error Handling in Elm. It's a union type. 

The definition looks like this:
```
type Result error value =
	Err error
	| OK value
```
This is similar to Maybe. This type has two variable types. An Error and Value. If it has gone good it goes down to the OK and shows the value. If it
has gone bad it goes down to the Error and show the error msg. With the 'Maybe' we would not see the error message but in 'Result we will. I would prefer 
this union type for Error handling.
* Can you reverse a string in Elm?
Yes
```
String.reverse "hej".
```
* Can you write a call to a HTTP endpoint?
You can build your own HTTP request or use HTTP.get (only if you have to use method 'GET')

Http.get:
```
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
    --   Http.send Counter (request decodeUrl)
```
Your own Http request:
```
   let
	 request = Http.request
     	       { method = "GET"
     	       , headers =  []
     	       , url = "http://localhost:3000/counter"
     	       , body = Http.emptyBody
	       , expect = Http.expectJson decodeUrl
      	       , timeout = Nothing
       	       , withCredentials = False
      	}
	in 
  	   Http.send Counter (request decodeUrl)
```

## 4. Haskell

* What is a side-effect?
* How do Haskell handle side-effects? What does 'IO ()' mean?
* What does it mean that functions are first-class citizens?
* What is recursion?
* What are some of the benefits of using Haskell?
