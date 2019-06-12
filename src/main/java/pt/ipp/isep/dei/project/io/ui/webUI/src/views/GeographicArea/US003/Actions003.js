import axios from 'axios';

export const FETCH_GA_STARTED = 'FETCH_GA_STARTED';
export const FETCH_GA_SUCCESS = 'FETCH_GA_SUCCESS';
export const FETCH_GA_FAILURE = 'FETCH_GA_FAILURE';


export function fetchGA({id, name, typeArea, latitude, longitude, altitude}) {
  const token = localStorage.getItem('loginToken');
  console.log(id, name, typeArea, latitude, longitude, altitude);
  return dispatch => {
    dispatch(fetchGAStarted(id, name, typeArea, latitude, longitude, altitude));
    const data = {id, name, typeArea, latitude, longitude, altitude};
    axios
      .post(`https://localhost:8443/geographic_area_settings/areas`, data, {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          body: {id, name, typeArea, local:{latitude, longitude, altitude}}
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

export function fetchGAStarted(id, name, typeArea, latitude, longitude, altitude) {
  return {
    type: FETCH_GA_STARTED,
    payload: {
      id: id,
      name: name,
      typeArea: typeArea,
      latitude: latitude,
      longitude: longitude,
      altitude: altitude
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



