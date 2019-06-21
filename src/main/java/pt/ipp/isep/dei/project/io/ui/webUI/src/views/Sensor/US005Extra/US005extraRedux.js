import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchSensorTypes} from './Actions005extra';
import Card from "reactstrap/es/Card";
import CardBody from "reactstrap/es/CardBody";
import US005 from "../US005";

class US005extraRedux extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchTypes();
  }

  render() {
    const {listSensorTypes} = this.props;
    return (
      <div style={{float:'left'}}>
        <Card><CardBody>
          <h5>List of sensor types:</h5>
          <p></p>
          {listSensorTypes.map(listSensorTypes => (
            <p
              key={listSensorTypes.id}>{'Type: [' + listSensorTypes.name + '] - Unit: [' + listSensorTypes.units + ']'}</p>
          ))}
        <US005/></CardBody>
        </Card>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    listSensorTypes: state.Reducer005extra.listSensorTypes
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchTypes: () => {
      dispatch(fetchSensorTypes())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US005extraRedux);
