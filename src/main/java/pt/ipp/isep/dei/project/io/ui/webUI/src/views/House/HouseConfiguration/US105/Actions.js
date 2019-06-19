import axios from 'axios';

export const REMOVE_GRID_ROOM_INFO_STARTED = 'REMOVE_GRID_ROOM_INFO_STARTED';
export const REMOVE_GRID_ROOM_INFO_SUCCESS = 'REMOVE_GRID_ROOM_INFO_SUCCESS';
export const REMOVE_GRID_ROOM_INFO_FAILURE = 'REMOVE_GRID_ROOM_INFO_FAILURE';
export const FETCH_NO_ROOM_GRID_DATA = 'FETCH_NO_ROOM_GRID_DATA';


export const fetchRoom = ({name, floor, width, length, height}) => {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchRoomInfo(name, floor, width, length, height));
    const data = {name, floor, width, length, height};
    axios
      .post('https://localhost:8443/houseSettings/room', data,
        {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          body: {name, floor, width, length, height}
        })
      .then(res => {
        dispatch(fetchRoomInfoSuccess(res.data));
      })
      .catch(err => {
        if (err.response === 400) {
          dispatch(fetchNoData1(err.message))
        }
        else {
          if(err.response !== undefined){
            dispatch(fetchRoomInfoFailure1(err.response.data));}
        }
      });
  };
};


export function fetchRoomInfo(name, floor, width, length, height) {
  return {
    type: REMOVE_GRID_ROOM_INFO_STARTED,
    payload: {
      name: name,
      floor: floor,
      width: width,
      length: length,
      height: height
    }
  }
}

export function fetchRoomInfoSuccess(data) {
  return {
    type: REMOVE_GRID_ROOM_INFO_SUCCESS,
    payload: {
      room: data
    }
  }
}

export function fetchRoomInfoFailure1(response) {
  return {
    type: REMOVE_GRID_ROOM_INFO_FAILURE,
    payload: {
      error: response
    }
  }
}

export function fetchNoData1(response) {
  return {
    type: FETCH_NO_ROOM_GRID_DATA,
    payload: {
      errorData: response
    }
  }
}
