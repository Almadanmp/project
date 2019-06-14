import {
  POST_AREATYPE_STARTED,
  POST_AREATYPE_SUCCESS,
  POST_AREATYPE_FAILURE,

} from './Actions001'

const initialState = {
  loading: false,
  error: null,
  typeInfo: []
};

export default function Reducer001(state = initialState, action) {
  switch (action.type) {
    case POST_AREATYPE_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        typeInfo: []
      };
    case POST_AREATYPE_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        typeInfo: [...action.payload.geographicAreaInfo]
      };
    case POST_AREATYPE_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        typeInfo: "ERROR: Wrong data inserted."
      };

    default:
      return state;
  }
}


