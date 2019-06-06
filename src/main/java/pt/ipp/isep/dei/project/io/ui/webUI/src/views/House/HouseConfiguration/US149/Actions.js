import axios from 'axios';

export const REMOVE_GRID_ROOM_INFO_STARTED = 'REMOVE_GRID_ROOM_INFO_STARTED';
export const REMOVE_GRID_ROOM_INFO_SUCCESS = 'REMOVE_GRID_ROOM_INFO_SUCCESS';
export const REMOVE_GRID_ROOM_INFO_FAILURE = 'REMOVE_GRID_ROOM_INFO_FAILURE';


export const fetchRoomFromGrid = ({roomID, gridID}) => {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchRoomFromGridInfo(roomID, gridID));
    axios
      .delete('https://localhost:8443/gridSettings/grids/' + gridID,
        {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          data: {name: roomID}
        })
      .then(res => {
        dispatch(fetchRoomFromGridInfoSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchRoomFromGridInfoFailure(err.message));
      });
  };
};


export function fetchRoomFromGridInfo(roomID, gridID) {
  return {
    type: REMOVE_GRID_ROOM_INFO_STARTED,
    payload: {
      roomID: roomID,
      gridID: gridID,
    }
  }
}

export function fetchRoomFromGridInfoSuccess(data) {
  return {
    type: REMOVE_GRID_ROOM_INFO_SUCCESS,
    payload: {
      message: data,
    }
  }
}

export function fetchRoomFromGridInfoFailure(message) {
  return {
    type: REMOVE_GRID_ROOM_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}
