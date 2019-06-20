import axios from 'axios';

export const FETCH_MOTHERCHILD_STARTED = 'FETCH_MOTHERCHILD_STARTED';
export const FETCH_MOTHERCHILD_SUCCESS = 'FETCH_MOTHERCHILD_SUCCESS';
export const FETCH_MOTHERCHILD_FAILURE = 'FETCH_MOTHERCHILD_FAILURE';


export const fetchMotherChild = ({motherId, childId}) => {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchMotherChildStarted(motherId, childId));
    const data = {motherId, childId};
    axios
      .put('https://localhost:8443/geoAreas/' + motherId + '/' + childId, data, {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          }}
      )
      .then(res => {
        dispatch(fetchMotherChildSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchMotherChildFailure(err.message))
      })

  };
};

export function fetchMotherChildStarted(motherId, childId) {
  return {
    type: FETCH_MOTHERCHILD_STARTED,
    payload: {
      motherId: motherId,
      childId: childId,
    }
  }
}

export function fetchMotherChildSuccess(data) {
  return {
    type: FETCH_MOTHERCHILD_SUCCESS,
    payload: {
      location: data
    }
  }
}

export function fetchMotherChildFailure(response) {
  return {
    type: FETCH_MOTHERCHILD_FAILURE,
    payload: {
      error: response
    }
  }
}



