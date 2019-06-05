import axios from 'axios';

export const FETCH_SENSOR_INFO_STARTED = 'FETCH_SENSOR_INFO_STARTED';
export const FETCH_SENSOR_INFO_SUCCESS = 'FETCH_SENSOR_INFO_SUCCESS';
export const FETCH_SENSOR_INFO_FAILURE = 'FETCH_SENSOR_INFO_FAILURE';


export const fetchSensor = ({name, floor, width, length, height}) => {
  return dispatch => {
    dispatch(fetchSensorInfo(name, floor, width, length, height)); // antes de fazer o get, coloca o loading a true
    const data = {name, floor, width, length, height};
    axios
      .post('http://localhost:9898/houseSettings/room', data, //falta autorização
        {
          headers: {'Content-Type': 'application/json'},
          body: {name, floor, width, length, height}
        })
      .then(res => {
        dispatch(fetchSensorInfoSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchSensorInfoFailure(err.message));
      });
  };
};


export function fetchSensorInfo(name, floor, width, length, height) {
  return {
    type: FETCH_SENSOR_INFO_STARTED,
    payload: {
      name: name,
      floor: floor,
      width: width,
      length: length,
      height: height
    }
  }
}

export function fetchSensorInfoSuccess(data) { // cria uma açao
  return {
    type: FETCH_SENSOR_INFO_SUCCESS,
    payload: {
      room: data //passa o array com os dados
    }
  }
}

export function fetchSensorInfoFailure(message) {
  return {
    type: FETCH_SENSOR_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}



