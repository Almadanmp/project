import {
  FETCH_HOT_STARTED,
  FETCH_HOT_SUCCESS,
  FETCH_HOT_FAILURE,

} from './Actions'


const initialstate = {
  loading: false,
  error: null,
  hottestDay: {}
};


export default function Reducers631(state = initialstate, action) {
  switch (action.type) {
    case FETCH_HOT_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        hottestDay: {}
      };
    case FETCH_HOT_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        hottestDay: {...action.payload.hottestDay}
      };
    case FETCH_HOT_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        hottestDay: {}
      };

    default:
      return state;
  }
}


