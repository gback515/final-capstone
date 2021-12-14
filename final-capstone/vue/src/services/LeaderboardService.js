import axios from 'axios';

export default {

    getLeaderboardById(leagueId) {
        return axios.get(`/leaderboard/${leagueId}`)
    },

}