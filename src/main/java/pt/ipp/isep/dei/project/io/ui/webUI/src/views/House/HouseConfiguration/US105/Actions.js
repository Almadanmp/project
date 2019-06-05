import axios from 'axios';

export const CREATE_ROOM_INFO_STARTED = 'CREATE_ROOM_INFO_STARTED';

export const fetchRoom = ({name, floor, width, length, height}) => {
  return dispatch => {
    dispatch(fetchRoomInfo(name, floor, width, length, height));
    const data = {name, floor, width, length, height};
    axios
      .post('http://localhost:9898/houseSettings/room', data,
        {
          headers: {'Content-Type': 'application/json'},
          body: {name, floor, width, length, height}
        })
  };
};

export function fetchRoomInfo(name, floor, width, length, height) {
  return {
    type: CREATE_ROOM_INFO_STARTED,
    payload: {
      name: name,
      floor: floor,
      width: width,
      length: length,
      height: height
    }
  }
}
