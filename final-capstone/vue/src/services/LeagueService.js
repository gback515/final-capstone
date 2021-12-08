import axios from 'axios';

export default {

    addLeague(newLeague) {
        return axios.post('/create-league', newLeague);
    },

    getMyLeagues(userId) {
        return axios.get(`/my-leagues/${userId}`)
    },

    getUserId(username) {
        return axios.get(`/${username}`)
    },

    getLeagueDetails(leagueId) {
        return axios.get(`/league/${leagueId}`)
    },

    addUserToLeague(leagueId, userId) {
        return axios.post(`/league/${leagueId}/addUser/${userId}`)
    }

}