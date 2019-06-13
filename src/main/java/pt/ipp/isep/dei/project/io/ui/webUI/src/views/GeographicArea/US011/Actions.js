import axios from 'axios';

export const REMOVE_AREA_SENSOR_INFO_STARTED = 'REMOVE_AREA_SENSOR_INFO_STARTED';
export const REMOVE_AREA_SENSOR_INFO_SUCCESS = 'REMOVE_AREA_SENSOR_INFO_SUCCESS';
export const REMOVE_AREA_SENSOR_INFO_FAILURE = 'REMOVE_AREA_SENSOR_INFO_FAILURE';


export const deleteSensorFromArea = ({id, sensorId}) => {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchSensorFromAreaInfo(id, sensorId));
    axios
      .delete('https://localhost:8443/geographic_area_settings/areas/' + id,
        {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          data: {sensorId: sensorId}
        })
      .then(res => {
        dispatch(fetchSensorFromAreaInfoSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchSensorFromAreaInfoFailure(err.message));
      });
  };
};


export function fetchSensorFromAreaInfo(id, sensorId) {
  return {
    type: REMOVE_AREA_SENSOR_INFO_STARTED,
    payload: {
      id: id,
      sensorId: sensorId,
    }
  }
}

export function fetchSensorFromAreaInfoSuccess(data) {
  return {
    type: REMOVE_AREA_SENSOR_INFO_SUCCESS,
    payload: {
      message: data,
    }
  }
}

export function fetchSensorFromAreaInfoFailure(message) {
  return {
    type: REMOVE_AREA_SENSOR_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}
