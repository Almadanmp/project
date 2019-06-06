import axios from 'axios';

export const FETCH_RAINFALL_STARTED = 'FETCH_RAINFALL_STARTED';
export const FETCH_RAINFALL_SUCCESS = 'FETCH_RAINFALL_SUCCESS';
export const FETCH_RAINFALL_FAILURE = 'FETCH_RAINFALL_FAILURE';


export const fetchTotalRainfallDay = ({ selectedDay }) => {
  const token = localStorage.getItem('loginToken')
  return dispatch => {
    dispatch(fetchTotalRainfallStarted(selectedDay)); // antes de fazer o get, coloca o loading a true
    axios
      .get(`https://localhost:8443/houseMonitoring/totalRainfall?date=`+selectedDay,  {
        headers: {
          'Authorization': token,
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json"}}
      )
      .then(res => {
        dispatch(fetchTotalRainfallSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchTotalRainfallFailure(err.message));
      });

  };
}

export function fetchTotalRainfallStarted (selectedDay) {
  return {
    type: FETCH_RAINFALL_STARTED,
    payload: {
      selectedDay: selectedDay
    }
  }
}

export function fetchTotalRainfallSuccess (data) { // cria uma açao
  return {
    type: FETCH_RAINFALL_SUCCESS,
    payload: {
      totalRainfall: data //passa o array com os dados
    }
  }
}

export function fetchTotalRainfallFailure (message) {
return {
  type: FETCH_RAINFALL_FAILURE,
  payload: {
    error: message
  }
}
}



