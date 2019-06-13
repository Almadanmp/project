import axios from 'axios';

export const FETCH_GAs_BY_TYPE_STARTED = 'FETCH_GAs_BY_TYPE_STARTED';
export const FETCH_GAs_BY_TYPE_SUCCESS = 'FETCH_GAs_BY_TYPE_SUCCESS';
export const FETCH_GAs_BY_TYPE_FAILURE = 'FETCH_GAs_BY_TYPE_FAILURE';

export function fetchGAByT() {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchGAsByTypeStarted());
    axios
      .get(`https://localhost:8443/geographic_area_settings/areaTypes`, {
          headers:
            {
              'Authorization':
              token,
              "Access-Control-Allow-Credentials":
                true,
              "Access-Control-Allow-Origin":
                "*",
              "Content-Type":
                "application7json"
            }
        }
      )

      .then(res => {
          dispatch(fetchGAsByTypeSuccess(res.data));
        }
      )
      .catch(err => {
        dispatch(fetchGAsByTypeFailure(err.message))
      });
  }
    ;
}

export function fetchGAsByTypeStarted() {
  return {
    type: FETCH_GAs_BY_TYPE_STARTED
  }
}

export function fetchGAsByTypeSuccess(data) {
  return {
    type: FETCH_GAs_BY_TYPE_SUCCESS,
    payload: {
      listGATypes: data
    }
  }
}

export function fetchGAsByTypeFailure(message) {
  return {
    type: FETCH_GAs_BY_TYPE_FAILURE,
    payload: {
      error: message
    }
  }
}
