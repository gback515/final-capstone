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
                    @click="center=m.position"/>
            </GmapMap>
      </div>
  </div>
</template>

<script>
import CourseService from "../services/CourseService.js"
export default {
    data() {
        return {
            center: { lat: 39.1556302, lng: -84.4262227 },
            currentPlace: null,
            markers: [],
            places: [],
            place: "",
            lat: 0,
            lng: 0,
        }
    },
    mounted() {
    this.geolocate();
  },
  methods: {
    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.markers.push({ position: marker });
        this.places.push(this.currentPlace);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    addCourses() {
        this.places.array.forEach(course => {
            const marker = {
                lat: this.lat = course.lat,
                lng: this.lng = course.lng
            }
            this.markers.push({ position: marker });
        });
    },
    geolocate: function() {
      navigator.geolocation.getCurrentPosition(position => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
    },
  },
  created() {
        CourseService.getCourses().then(response => {
        for (let course of response.data) {
            const marker = {
                lat: this.lat = parseFloat(course.lat),
                lng: this.lng = parseFloat(course.lng)
            }
            this.markers.push({ position: marker });
            this.places.push(marker);
        }
        }).catch(error => {
            console.log("caught error" + error)
        })
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