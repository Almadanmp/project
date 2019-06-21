import axios from 'axios';

export const FETCH_MOTHERCHILD_STARTED = 'FETCH_MOTHERCHILD_STARTED';
export const FETCH_MOTHERCHILD_SUCCESS = 'FETCH_MOTHERCHILD_SUCCESS';
export const FETCH_MOTHERCHILD_FAILURE = 'FETCH_MOTHERCHILD_FAILURE';


export const fetchMotherChild = ({linkAdd, id}) => {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchMotherChildStarted(linkAdd, id));
    console.log(id)
    console.log(linkAdd)
    const data = {linkAdd, id};
    axios
      .put(linkAdd + id, data, {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },


      }
      )
      .then(res => {
        dispatch(fetchMotherChildSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchMotherChildFailure(err.message))
      })

  };
};

export function fetchMotherChildStarted(linkAdd, id) {
  return {
    type: FETCH_MOTHERCHILD_STARTED,
    payload: {
      linkAdd: linkAdd,
      id: id,
    }
  }
}

export function fetchMotherChildSuccess(data) {
  return {
    type: FETCH_MOTHERCHILD_SUCCESS,
    payload: {
      added: data
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



