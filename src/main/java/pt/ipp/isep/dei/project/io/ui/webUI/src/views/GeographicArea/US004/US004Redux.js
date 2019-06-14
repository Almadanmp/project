import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchGABTs} from './Actions004';

class US004Redux extends Component {
  constructor(props) {
    super(props);
    this.state = {
      typeArea: ''
    }
  }

  componentDidMount() {
    this.props.onFetchByType();
  }

  render() {
    const {listGABTypes} = this.props;
    return (
      <>
        <label>
          <h5>Existing geographic area by type:</h5>
          <p></p>
          {listGABTypes.map(listGABTypes => (
          <p key={listGABTypes.id}>{'- ' + listGABTypes.name}</p>
        ))}
        </label>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    listGABTypes: state.Reducers004.listGABTypes
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchByType: () => {
      dispatch(fetchGABTs())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US004Redux);
