import axios from 'axios';

export default {
    addCourse(newCourse) {
        return axios.post('/add-course', newCourse);
    }
}