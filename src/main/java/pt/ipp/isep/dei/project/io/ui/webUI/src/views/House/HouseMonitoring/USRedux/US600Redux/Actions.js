import axios from 'axios';

export const FETCH_T_STARTED = 'FETCH_T_STARTED';
export const FETCH_T_SUCCESS = 'FETCH_T_SUCCESS';
export const FETCH_T_FAILURE = 'FETCH_T_FAILURE';


export function fetchTemp () {
  return dispatch => {
    dispatch(fetchTempStarted()); // antes de fazer o get, coloca o loading a true
    axios
      .get(`http://localhost:9898/houseMonitoring/currentHouseAreaTemperature`)
      .then(res => {
        dispatch(fetchTempSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchTempFailure(err.message));
      });

  };
}

export function fetchTempStarted () {
  return {
    type: FETCH_T_STARTED
  }
}

export function fetchTempSuccess (data) { // cria uma açao
  return {
    type: FETCH_T_SUCCESS,
    payload: {
     temp: data //passa o array com os dados
    }
  }
}

export function fetchTempFailure (message) {
return {
  type: FETCH_T_FAILURE,
  payload: {
    error: message
  }
}
}



