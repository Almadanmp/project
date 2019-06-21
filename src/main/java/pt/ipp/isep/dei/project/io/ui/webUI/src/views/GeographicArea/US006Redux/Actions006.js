import axios from 'axios';

export const ADD_AREA_SENSOR_STARTED = 'ADD_AREA_SENSOR_STARTED';
export const ADD_AREA_SENSOR_SUCCESS = 'ADD_AREA_SENSOR_SUCCESS';
export const ADD_AREA_SENSOR_FAILURE = 'ADD_AREA_SENSOR_FAILURE';


export const fetchLocation = ({geographicAreaId, typeSensor, name, sensorId, dateStartedFunctioning, latitude, longitude, altitude}) => {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchLocationStarted(geographicAreaId, typeSensor, name, sensorId, dateStartedFunctioning, latitude, longitude, altitude));
    const data = {geographicAreaId, typeSensor, name, sensorId, dateStartedFunctioning, latitude, longitude, altitude};
    axios
      .post('https://localhost:8443/sensors/areas/' + geographicAreaId + '/sensors', data, {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          body: {typeSensor, name, sensorId, dateStartedFunctioning, latitude, longitude, altitude}
        }
      )
      .then(res => {
        dispatch(fetchLocationSuccess(res.data));
      })
      .catch(err => {
        console.log("olaaa");
        dispatch(fetchLocationFailure(err.message))
      })

  };
};

export function fetchLocationStarted(geographicAreaId, typeSensor, name, sensorId, dateStartedFunctioning, latitude, longitude, altitude) {
  return {
    type: ADD_AREA_SENSOR_STARTED,
    payload: {
      geographicAreaId: geographicAreaId,
      typeSensor: typeSensor,
      name: name,
      sensorId: sensorId,
      dateStartedFunctioning: dateStartedFunctioning,
      latitude: latitude,
      longitude: longitude,
      altitude: altitude,
    }
  }
}

export function fetchLocationSuccess(data) {
  return {
    type: ADD_AREA_SENSOR_SUCCESS,
    payload: {
      addedSensor: data
    }
  }
}

export function fetchLocationFailure(response) {
  return {
    type: ADD_AREA_SENSOR_FAILURE,
    payload: {
      error: response
    }
  }
}



