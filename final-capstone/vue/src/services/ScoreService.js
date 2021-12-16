import axios from 'axios';

export default {
    addScore(newScore) {
        return axios.post(`/add-score`, newScore);
    },
    getScore() {
        return axios.get(`/score`)
    },
    getScoreByUserId(userId) {
        return axios.get(`/score/user/${userId}`)
    },
    getScoreByTeeTimeId(teeTimeId) {
        return axios.get(`/score/${teeTimeId}`)
    },
    addScoreToUser(userId) {
        return axios.post(`/add-score/user/${userId}`)
    },
    getScoreByLeagueId(leagueId) {
        return axios.get(`/score/league/${leagueId}`)
    }
}
