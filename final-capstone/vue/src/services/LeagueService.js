import axios from 'axios';

export default {

    addLeague(newLeague) {
        return axios.post('/create-league', newLeague);
    },

    getMyLeagues(userId) {
        return axios.get(`/leagues/${userId}`)
    },

    getLeague(leagueId) {
        return axios.get(`/league/${leagueId}`)
    },

    addUserToLeague(userLeague) {
        return axios.post(`/league/addUser`, userLeague)
    },

    getLeagueByLeagueName(leagueName) {
        return axios.get(`/league/${leagueName}`)
    },

    getAllLeagues() {
        return axios.get(`/leagues`)
    },

    getMembersByLeague(leagueId) {
        return axios.get(`/league/${leagueId}/get-members`)
    },
}