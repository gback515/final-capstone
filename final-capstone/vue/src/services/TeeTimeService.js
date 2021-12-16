import axios from 'axios';

export default {
    getTeeTimes() {
        return axios.get(`/tee-time`);
    },
    addTeeTime(leagueId, newTeeTime) {
        return axios.post(`/create-tee-time/${leagueId}`, newTeeTime);
    },
    getTeeTimeByUser(userId) {
        return axios.get(`/tee-time/${userId}`);
    },
    addScoreToTeeTime(teeTimeId, score) {
        return axios.post(`/tee-time/${teeTimeId}/score/${score}`)
    },
    getTeeTimeByLeague(leagueId) {
        return axios.get(`tee-time/league/${leagueId}`)
    }
}