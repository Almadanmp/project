import axios from 'axios';

export const FETCH_GA_STARTED = 'FETCH_GA_STARTED';
export const FETCH_GA_SUCCESS = 'FETCH_GA_SUCCESS';
export const FETCH_GA_FAILURE = 'FETCH_GA_FAILURE';


export function fetchGA({id, name, typeArea, length, width, latitude, longitude, altitude, description}) {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchGAStarted(id, name, typeArea, length, width, latitude, longitude, altitude, description));
    const data = {id, name, typeArea, length, width, latitude, longitude, altitude, description};
    axios
      .post(`https://localhost:8443/geographic_area_settings/areas`, data, {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          body: {
            "id": {id},
            "name": {name},
            "typeArea": {typeArea},
            "length": {length},
            "width": {width},
            "local": {
              "latitude": {latitude},
              "longitude": {longitude},
              "altitude": {altitude},
            },
            "description": {description}
          }
        }
      )
      .then(res => {
        dispatch(fetchGASuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchGAFailure(err.message));
      });

  };
}

export function fetchGAStarted(id, name, typeArea, length, width, latitude, longitude, altitude, description) {
  return {
    type: FETCH_GA_STARTED,
    payload: {
      id: id,
      name: name,
      typeArea: typeArea,
      length: length,
      width: width,
      local: {
        latitude: latitude,
        longitude: longitude,
        altitude: altitude
      },
      description: description
    }
  }
}

export function fetchGASuccess(data) {
  return {
    type: FETCH_GA_SUCCESS,
    payload: {
      geographicAreaInfo: data
    }
  }
}

export function fetchGAFailure(message) {
  return {
    type: FETCH_GA_FAILURE,
    payload: {
      error: message
    }
  }
}



