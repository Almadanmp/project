import axios from 'axios';

export const FETCH_ENERGYGRID_INFO_STARTED = 'FETCH_ENERGYGRID_INFO_STARTED';
export const FETCH_ENERGYGRID_INFO_SUCCESS = 'FETCH_ENERGYGRID_INFO_SUCCESS';
export const FETCH_ENERGYGRID_INFO_FAILURE = 'FETCH_ENERGYGRID_INFO_FAILURE';


export const fetchEnergyGrid = ({name, maxContractedPower}) => {
  return dispatch => {
    dispatch(fetchEnergyGridInfo(name, maxContractedPower)); // antes de fazer o get, coloca o loading a true
    const houseID = "01";
    const data = {name, houseID, maxContractedPower};
    axios
      .post('http://localhost:9898/gridSettings/grids', data, //falta autorização
        {
          headers: {'Content-Type': 'application/json'},
          body: {name, houseID, maxContractedPower}
        })
      .then(res => {
        dispatch(fetchEnergyGridInfoSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchEnergyGridInfoFailure(err.message));
      });
  };
};


export function fetchEnergyGridInfo(name, maxContractedPower) {
  return {
    type: FETCH_ENERGYGRID_INFO_STARTED,
    payload: {
      name: name,
      houseID: "01",
      maxContractedPower: maxContractedPower
    }
  }
}

export function fetchEnergyGridInfoSuccess(data) { // cria uma açao
  return {
    type: FETCH_ENERGYGRID_INFO_SUCCESS,
    payload: {
      room: data //passa o array com os dados
    }
  }
}

export function fetchEnergyGridInfoFailure(message) {
  return {
    type: FETCH_ENERGYGRID_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}
