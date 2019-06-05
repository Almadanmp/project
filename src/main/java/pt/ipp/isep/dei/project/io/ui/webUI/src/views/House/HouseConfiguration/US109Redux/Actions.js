import axios from 'axios';

export const FETCH_EDIT_ROOM_STARTED = 'FETCH_EDIT_ROOM_STARTED';
export const FETCH_EDIT_ROOM_SUCCESS = 'FETCH_EDIT_ROOM_SUCCESS';
export const FETCH_EDIT_ROOM_FAILURE = 'FETCH_EDIT_ROOM_FAILURE';


export function onfetchEditRoom({ name, floor, width, length, height }) {
  return dispatch => {
    dispatch(fetchEditRoomStarted(name, floor, width, length, height)); // antes de fazer o get, coloca o loading a true
    axios
      .put(`http://localhost:9898/houseSettings/room`,{
        body: {
          name: name,
          floor: floor,
          width: width,
          length: length,
          height: height

        }}
    )
      .then(res => {
        dispatch(fetchEditRoomSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchEditRoomFailure(err.message));
      });

  };
}

export function fetchEditRoomStarted(name, floor, width, length, height) {
  return {
    type: FETCH_EDIT_ROOM_STARTED,
    payload: {
      body: {
        name: name,
        floor: floor,
        width: width,
        length: length,
        height: height
      }
  }
  }
}

export function fetchEditRoomSuccess(data) { // cria uma açao
  return {
    type: FETCH_EDIT_ROOM_SUCCESS,
    payload: {
      editRoom: [...data] //passa o array com os dados
    }
  }
}

export function fetchEditRoomFailure(message) {
  return {
    type: FETCH_EDIT_ROOM_FAILURE,
    payload: {
      error: message
    }
  }
}



