import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LeagueList from "../components/LeagueList"
import LeagueForm from '../components/LeagueForm'
import AddGolfer from "../views/AddGolfer.vue"
import AddCourse from "../views/AddCourse.vue"
import LeagueDetails from "../components/LeagueDetails"
import CourseList from "../views/CourseList"
import CourseDetails from "../components/CourseDetails"
import LeaderboardDetails from "@/views/LeaderboardDetails"
import SearchCourses from "../components/SearchCourses"
import ScoresForm from "../components/ScoresForm"
import CreateTeeTime from "../components/CreateTeeTime"
import TeeTimeDetails from "../components/TeeTimeDetails"

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/leagues/:userId",
      name: "leagues",
      component: LeagueList,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/create-league",
      name: "create-league",
      component: LeagueForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/league/:leagueId",
      name: "league-details",
      component: LeagueDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/add-golfer",
      name: "add-golfer",
      component: AddGolfer,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/add-course",
      name: "add-course",
      component: AddCourse,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/search-courses",
      name: "search-courses",
      component: SearchCourses,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/course-list",
      name: "course-list",
      component: CourseList,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/course/id/:courseId",
      name: "course-details",
      component: CourseDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/leaderboard/:leagueId",
      name: "leaderboard-details",
      component: LeaderboardDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/create-score/:teeTimeId",
      name: "create-score",
      component: ScoresForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/tee-time/league/:leagueId",
      name: "tee-time",
      component: CreateTeeTime,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/tee-time/:teeTimeId",
      name: "teeTimeDetails",
      component: TeeTimeDetails,
      meta: {
        requiresAuth: true
      }
    },


  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
