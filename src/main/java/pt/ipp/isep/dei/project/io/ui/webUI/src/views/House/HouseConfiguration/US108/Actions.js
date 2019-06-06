import axios from 'axios';

export const FETCH_GAS_STARTED = 'FETCH_GAS_STARTED';
export const FETCH_GAS_SUCCESS = 'FETCH_GAS_SUCCESS';
export const FETCH_GAS_FAILURE = 'FETCH_GAS_FAILURE';


export function fetchGAs() {
  return dispatch => {
    dispatch(fetchGAsStarted()); // antes de fazer o get, coloca o loading a true
    axios
      .get(`http://localhost:9898/houseSettings/houseRooms`)
      .then(res => {
        dispatch(fetchGAsSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
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

export function fetchGAsSuccess(data) { // cria uma açao
  return {
    type: FETCH_GAS_SUCCESS,
    payload: {
      data: [...data] //passa o array com os dados
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



