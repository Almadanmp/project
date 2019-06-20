import {POST_FILE_FAILURE, POST_FILE_STARTED, POST_FILE_SUCCESS} from "./UploadGAActions";

const initialState = {
  loading: false,
  error: null,
  file: null,
};


export default function ReducersUpload(state = initialState, action) {
  switch (action.type) {
    case POST_FILE_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        file: null,
        results: null,
      };
    case POST_FILE_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        file: action.payload.data,
        results: 'success'
      };
    case POST_FILE_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        results: 'failure'
      };
    default:
      return state;
  }
}
