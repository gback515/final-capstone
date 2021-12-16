import axios from 'axios';

export default {
    getTeeTimes() {
        return axios.get(`/tee-time`);
    },
    addTeeTime(leagueId, userId, newTeeTime) {
        return axios.post(`/create-tee-time/${leagueId}/${userId}`, newTeeTime);
    },
    getTeeTimeByUser(userId) {
        return axios.get(`/tee-time/user/${userId}`);
    },
    // addScoreToTeeTime(teeTimeId, score) {
    //     return axios.post(`/tee-time/${teeTimeId}/score/${score}`)
    // },
    getTeeTimeByLeague(leagueId) {
        return axios.get(`tee-time/league/${leagueId}`)
    },
    getTeeTimeById(teeTimeId) {
        return axios.get(`tee-time/id/${teeTimeId}`)
    }
}