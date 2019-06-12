import {
  FETCH_GA_STARTED,
  FETCH_GA_SUCCESS,
  FETCH_GA_FAILURE,

} from './Actions003'

const initialstate = {
  loading: false,
  error: null,
  geographicAreaInfo: []
};

export default function Reducer003(state = initialstate, action) {
  switch (action.type) {
    case FETCH_GA_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        geographicAreaInfo: []
      };
    case FETCH_GA_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        geographicAreaInfo: [...action.payload.geographicAreaInfo]
      };
    case FETCH_GA_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        geographicAreaInfo: " ERROR: NO DATA Available"
      };

    default:
      return state;
  }
}


