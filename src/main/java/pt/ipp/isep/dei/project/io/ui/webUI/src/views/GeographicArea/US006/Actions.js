import axios from 'axios';

export const FETCH_SENSOR_INFO_STARTED = 'FETCH_SENSOR_INFO_STARTED';
export const FETCH_SENSOR_INFO_SUCCESS = 'FETCH_SENSOR_INFO_SUCCESS';
export const FETCH_SENSOR_INFO_FAILURE = 'FETCH_SENSOR_INFO_FAILURE';


export const fetchSensor = ({roomID, typeSensor,name,sensorId,dateStartedFunctioning}) => {
  const token = localStorage.getItem('loginToken')
  return dispatch => {
    dispatch(fetchSensorInfo(roomID, typeSensor, name, sensorId, dateStartedFunctioning)); // antes de fazer o get, coloca o loading a true
    const data = {roomID, typeSensor, name, sensorId, dateStartedFunctioning};
    axios
      .post('https://localhost:8443/roomConfiguration/rooms/'+roomID+'/sensors', data, //falta autorização
        {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          body: { sensorId,name, dateStartedFunctioning, typeSensor}
        })
      .then(res => {
        dispatch(fetchSensorInfoSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchSensorInfoFailure(err.message));
      });
  };
};


export function fetchSensorInfo(roomID, typeSensor,name,sensorId,dateStartedFunctioning) {
  return {
    type: FETCH_SENSOR_INFO_STARTED,
    payload: {
      roomID : roomID,
      typeSensor:typeSensor,
      name: name,
      sensorId: sensorId,
      dateStartedFunctioning: dateStartedFunctioning,
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



