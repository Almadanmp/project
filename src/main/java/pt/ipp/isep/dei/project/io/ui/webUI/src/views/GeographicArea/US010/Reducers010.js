import {
  FETCH_AREA_SENSORS_STARTED,
  FETCH_AREA_SENSORS_SUCCESS,
  FETCH_AREA_SENSORS_FAILURE,

} from './Actions010'

const initialstate = {
  loading: false,
  error: null,
  rooms: []
};

export default function Reducer010(state = initialstate, action) {
  switch (action.type) {
    case FETCH_AREA_SENSORS_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        sensor: []
      };
    case FETCH_AREA_SENSORS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        sensor: [...action.payload.sensor]
      };
    case FETCH_AREA_SENSORS_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        sensor: []
      };

    default:
      return state;
  }
}


