<template>
  <div class="search-main">
      <div class="search-bar">
          <h2>Search for Golf Course</h2>
          <GmapAutocomplete class="search"
          @place_changed="setPlace"/>
          <button id="add" class="search" @click='addMarker'>Add</button>
      </div>
      <div class="map">
          <GmapMap
            :center="center"
            :zoom="9"
            style="width: 100%; height: 400px;">
                <GmapMarker
                    :key="index"
                    v-for="(m, index) in markers"
                    :position="m.position"
                    :clickable="true"
                    @click="center=m.position; setPlace(m); setMarker(m); toggleInfoWindow(m, index)"
                  >
                </GmapMarker>
                <gmap-info-window :options="infoOptions" :position="infoWindowPos" :opened="infoWinOpen" @closeclick="infoWinOpen=false" />
            </GmapMap>
      </div>
  </div>
</template>

<script>
import CourseService from "../services/CourseService.js"
import {gmapApi} from 'vue2-google-maps'

export default {
    data() {
        return {
            center: { lat: 39.1556302, lng: -84.4262227 },
            currentPlace: null,
            markers: [],
            currentMarker: {
              infoText: ""
            },
            places: [],
            place: "",
            lat: 0,
            lng: 0, 
            infoWindowPos: null,
            infoWinOpen: false,
            currentMidx: false,
            infoOptions: {
              content: '',
              pixelOffset: {
                width: 0,
                height: -35
            }
          },
        }
    },
    mounted() {
    this.geolocate();
  },
  methods: {
    toggleInfoWindow(marker, index) {
      this.infoWindowPos = marker.position;
      this.infoOptions.content = this.currentMarker.infoText;

      if (this.currentMidx == index) {
        this.infoWinOpen = !this.infoWinOpen;
      }
      else {
        this.infoWinOpen = true;
        this.currentMidx = index;
      }
    },
    setPlace(place) {
      this.currentPlace = place;
    },
    setMarker(marker) {
      console.log(marker);
      this.currentMarker = marker;
     //this.currentMarker.infoText = marker.infoText;
    },
    addMarker() {
      if (this.currentPlace) {
        console.log(this.currentPlace);
        const marker = {
        position: {
            lat: this.currentPlace.geometry.location.lat(),
            lng: this.currentPlace.geometry.location.lng(),
         },
          infoText: `<strong>${this.currentPlace.name}</strong>`
        };
        this.markers.push(marker);
        this.places.push(this.currentPlace);
        this.setMarker(marker);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    geolocate: function() {
      navigator.geolocation.getCurrentPosition(position => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
    },
    markerClicked(m) {
      m
    },
    showInfo() {
      const infoWindow = new this.google.maps.InfoWindow({
        content: `<h3>${this.currentPlace.label}</h3>`
      });
      infoWindow.open({
        //anchor: marker,
        shouldFocus: false,
      });
    }
  },
  computed: {
    google: gmapApi
  }, 
  created() {
        CourseService.getCourses().then(response => {
        for (let course of response.data) {
            const marker = {
              position: {
                lat: this.lat = parseFloat(course.lat),
                lng: this.lng = parseFloat(course.lng),
              },
                infoText: `<strong>${course.course_name}</strong>`
            }
            this.markers.push(marker);
            this.places.push(marker);
        }
        }).catch(error => {
            console.log("caught error" + error)
        });
  }
}
</script>

<style>
.search-main {
    width: 85vw;
    height: 100vh;
}

.map {
    border: black 1px solid;
    margin: 5% 20% 20% 20%;
}

.search-bar {
    margin: 2% 38% -3%;
    width: 80%;
}

.search {
    margin-left: 3%
}

#add {
    margin: 0;
}
</style>