import axios from 'axios';

export const FETCH_COLD_STARTED = 'FETCH_COLD_STARTED';
export const FETCH_COLD_SUCCESS = 'FETCH_COLD_SUCCESS';
export const FETCH_COLD_FAILURE = 'FETCH_COLD_FAILURE';


export const fetchColdDay = ({ from, to }) => {
  return dispatch => {
    dispatch(fetchColdDayStarted(from, to)); // antes de fazer o get, coloca o loading a true
    axios
      .get(`http://localhost:9898/houseMonitoring/coldestDay?initialDate=`+from+`&finalDate=`+to, {
      })

      .then(res => {
        dispatch(fetchColdDaySuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchColdDayFailure(err.message));
      });

  };
}

export function fetchColdDayStarted (from, to) {
  return {
    type: FETCH_COLD_STARTED,
    payload: {
      from: from,
      to: to//passa o array com os dados
    }
  }
}

export function fetchColdDaySuccess (data) { // cria uma açao
  return {
    type: FETCH_COLD_SUCCESS,
    payload: {
      cold: data //passa o array com os dados
    }
  }
}

export function fetchColdDayFailure (message) {
return {
  type: FETCH_COLD_FAILURE,
  payload: {
    error: message
  }
}
}



