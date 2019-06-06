import axios from "axios";

export function logInUser(username, password) {
  return axios
    .post('https://localhost:8443/login', {username, password}, {
      headers: new Headers({
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }),
    })
    .then(res => {
      localStorage.setItem('loginToken', res.headers.authorization)
      return Promise.resolve(res);
    });
}
