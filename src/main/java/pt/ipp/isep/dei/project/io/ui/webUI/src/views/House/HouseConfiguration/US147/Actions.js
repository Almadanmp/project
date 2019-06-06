import axios from 'axios';

export const FETCH_ROOM_GRID_INFO_STARTED = 'FETCH_ROOM_GRID_INFO_STARTED';
export const FETCH_ROOM_GRID_INFO_SUCCESS = 'FETCH_ROOM_GRID_INFO_SUCCESS';
export const FETCH_ROOM_GRID_INFO_FAILURE = 'FETCH_ROOM_GRID_INFO_FAILURE';


export const fetchRoomGrid = ({name, grid}) => {
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchRoomGridInfo(name, grid));
    const data = {name, grid};
    axios
      .post('https://localhost:8443/gridSettings/grids/' + grid, data,
        {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          },
          body: {name}
        })
      .then(res => {
        dispatch(fetchRoomGridInfoSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchRoomGridInfoFailure(err.message));
      });
  };
};


export function fetchRoomGridInfo(name, grid) {
  return {
    type: FETCH_ROOM_GRID_INFO_STARTED,
    payload: {
      name: name,
      grid: grid
    }
  }
}

export function fetchRoomGridInfoSuccess(data) {
  return {
    type: FETCH_ROOM_GRID_INFO_SUCCESS,
    payload: {
      room: data
    }
  }
}

export function fetchRoomGridInfoFailure(message) {
  return {
    type: FETCH_ROOM_GRID_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}



