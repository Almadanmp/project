import axios from 'axios';

export const POST_SENSOR_TYPE_STARTED = 'POST_SENSOR_TYPE_STARTED';
export const POST_SENSOR_TYPE_SUCCESS = 'POST_SENSOR_TYPE_SUCCESS';
export const POST_SENSOR_TYPE_FAILURE = 'POST_SENSOR_TYPE_FAILURE';


export function addSensorType(name, units) {
  const token = localStorage.getItem('loginToken');
  const data = {name, units};
  return dispatch => {
    dispatch(addSensorTypeStarted(name, units));
    axios
      .post(`https://localhost:8443/sensorsettings/sensorTypes`, data, {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          body: {
            name, units
          }
        }
      )
      .then(res => {
        dispatch(addSensorTypeSuccess(res.data));
      })
      .catch(err => {
        dispatch(addSensorTypeFailure(err.message));
      });

  };
}

export function addSensorTypeStarted(name, units) {
  return {
    type: POST_SENSOR_TYPE_STARTED,
    payload: {
      name: name,
      units: units
    }
  }
}

export function addSensorTypeSuccess(data) {
  return {
    type: POST_SENSOR_TYPE_SUCCESS,
    payload: {
      addedType: data
    }
  }
}

export function addSensorTypeFailure(message) {
  return {
    type: POST_SENSOR_TYPE_FAILURE,
    payload: {
      error: message
    }
  }
}
