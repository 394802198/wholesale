﻿var geocoder;var map;window.onload=function(){initialize()};function initialize(){geocoder=new google.maps.Geocoder();var latlng=new google.maps.LatLng(-36.88554,174.71380840000006);var myOptions={zoom:15,center:latlng,mapTypeId:google.maps.MapTypeId.ROADMAP};map=new google.maps.Map($("map_canvas"),myOptions);var input=$('address');new google.maps.places.Autocomplete(input)};function $(id){return document.getElementById(id)}