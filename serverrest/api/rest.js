var express = require('express');
var router = express.Router();

var counter = 0;

router.get('/counter', function(req, res, next) {
    counter++;
    let json = {"counter": counter};
    res.end(JSON.stringify(json));
});

router.put('/counter/:value', function(req, res, next) {
    counter = req.params.value;
    let json = {"counter": counter}
    res.end(JSON.stringify(json));
});



module.exports = router;