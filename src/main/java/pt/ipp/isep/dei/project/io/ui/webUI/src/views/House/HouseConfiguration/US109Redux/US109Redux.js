import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Button, Card, CardBody, Col, Row, Table} from "reactstrap";
import {onfetchEditRoom} from './Actions';

class US109Redux extends Component {
  constructor(props) {
    super(props);
    this.state = {
      roomID: ""
    }
    this.state = this.getInitialState();
    this.onSubmitHandler = this.onSubmitHandler.bind(this);
  }

  getInitialState() {
    return {

      name: undefined,
      floor: undefined,
      width: undefined,
      length: undefined,
      height: undefined

    };
  }

  // componentDidMount() {
  //   this.props.onFetchEditedRoom(this.state.name, this.state.floor, this.state.width, this.state.length, this.state.height);
  // }

  onChangeHandler = (name, floor, width, length, height) => {
    this.setState({
      body: {
        name: name,
        floor: floor,
        width: width,
        length: length,
        height: height
      }
    })
  }

  getRoom = () => {
    console.log("getRoom: " + JSON.stringify(this.props))
    this.props.getBody(this.state.body);
  }

  onSubmitHandler = () => {
    this.props.onFetchEditedRoom(this.state);
  }

  render() {
    return (
      <>

        <label>
          Floor:
          <input type="text" name="Floor" value={this.state.floor} onChange={this.onChangeHandler}/>
        </label>

        <label>
          Width:
          <input type="text" name="width" value={this.state.width} onChange={this.onChangeHandler}/>
        </label>

        <label>
          Length:
          <input type="text" name="length" value={this.state.length} onChange={this.onChangeHandler}/>
        </label>

        <label>
          Height:
          <input type="text" name="height" value={this.state.height} onChange={this.onChangeHandler}/>
        </label>

        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.onSubmitHandler}>
          Submit
        </Button>

      </>
    )
  }


}

const mapStateToProps = (state) => {
    return {
      loading: state.Reducers109.loading,
      editRoom: state.Reducers109.editRoom,
      error: state.Reducers109.error,
      body: {
        name: state.name,
        floor: state.floor,
        width: state.width,
        length: state.length,
        height: state.height
      }

    }
  }

;

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchEditedRoom: (name, floor, width, length, height) => {
      dispatch(onfetchEditRoom({name, floor, width, length, height}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US109Redux);
