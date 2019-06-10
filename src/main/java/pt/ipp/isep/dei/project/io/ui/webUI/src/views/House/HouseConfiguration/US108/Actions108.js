import axios from 'axios';

export const FETCH_GAS_STARTED = 'FETCH_GAS_STARTED';
export const FETCH_GAS_SUCCESS = 'FETCH_GAS_SUCCESS';
export const FETCH_GAS_FAILURE = 'FETCH_GAS_FAILURE';


export function fetchGAs() {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchGAsStarted());
    axios
      .get(`https://localhost:8443/houseSettings/houseRooms`, {
        headers: {
          'Authorization': token,
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json"
        }
      })
      .then(res => {
        dispatch(fetchGAsSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchGAsFailure(err.message));
      });

  };
}

export function fetchGAsStarted() {
  return {
    type: FETCH_GAS_STARTED
  }
}

export function fetchGAsSuccess(data) {
  return {
    type: FETCH_GAS_SUCCESS,
    payload: {
      data: [...data]
    }
  }
}

export function fetchGAsFailure(message) {
  return {
    type: FETCH_GAS_FAILURE,
    payload: {
      error: message
    }
  }
}



