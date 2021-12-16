import axios from 'axios';

export default {
    addScore(newScore) {
        return axios.post('/create-score', newScore);
    },
    getScore() {
        return axios.get('/score')
    },
    getScoreByUserId(userId) {
        return axios.get(`/score/user/${userId}`)
    },
    getScoreByTeeTimeId(teeTimeId) {
        return axios.get(`/score/${teeTimeId}`)
    },
    addScoreToTeeTime(teeTimeId) {
        return axios.post(`/create=score/${teeTimeId}`)
    },
    getScoreByLeagueId(leagueId) {
        return axios.get(`/score/league/${leagueId}`)
    }
}
