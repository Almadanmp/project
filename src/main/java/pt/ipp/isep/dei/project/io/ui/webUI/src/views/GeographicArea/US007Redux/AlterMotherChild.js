import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";
import {connect} from 'react-redux';

class CreateLocation extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  render() {
    const {location, error} = this.props;
    console.log(location)
    const {motherId, childId} = this.props;
    if ((location.toString()).indexOf("409") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Geographic area with ID: {childId} already belongs to
            geographic with ID: {motherId}.</Alert></div>
        </div>
      )
    }
    else if ((location.toString()).indexOf("422") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">You can't add a Geographic Area to itself.</Alert></div>
        </div>
      )
    }
    else if ((location.toString()).indexOf("403") !== -1 || (location.toString()).indexOf("404") !== -1 ||
      (location.toString()).indexOf("405") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Please select both
            Geographic Areas.</Alert></div>
        </div>
      )
    }
    else {
      return (
        <div className="help-block"><Alert color="success">
          <p>Geographic area with ID: {childId} was added to geographic are with ID: {motherId}.</p>
        </Alert></div>
      );
    }
  }
}


const mapStateToProps = (state) => {
  return {
    loading: state.Reducers007.loading,
    location: state.Reducers007.location,
    error: state.Reducers007.error
  }
};


export default connect(mapStateToProps, null)(CreateLocation);
