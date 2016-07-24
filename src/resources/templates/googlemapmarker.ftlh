<!DOCTYPE html>
<html>
<head>
    <script src="http://maps.googleapis.com/maps/api/js"></script>
    <script>
        function initialize() {

            var pinColor = "2E64FE";

            var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
                    new google.maps.Size(21, 34),
                    new google.maps.Point(0,0),
                    new google.maps.Point(10, 34));
            var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                    new google.maps.Size(40, 37),
                    new google.maps.Point(0, 0),
                    new google.maps.Point(12, 35));

            var myLocation = new google.maps.LatLng(${myLocationLat},${myLocationLng});

            var zoom = ${zoom};

            var locations = ${locations};

            var mapProp = {
                //send via java
                center: myLocation,
                zoom: zoom,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };

            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

            var myLocationMarker = new google.maps.Marker({
                position: myLocation,
                map: map,
                icon:pinImage,
                shadow:pinShadow
            });

            var infowindow = new google.maps.InfoWindow();

            var marker, i;

            for (i = 0; i < locations.length; i++) {
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(locations[i][0], locations[i][1]),
                    map: map
                });

                google.maps.event.addListener(marker, 'click', (function (marker, i) {
                    return function () {
                        //infowindow.setContent(locations[i][0]);
                        infowindow.open(map, marker);
                    }
                })(marker, i));
            }

        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>

<body>
<div id="googleMap" style="width:600px;height:600px;"></div>
</body>

</html>