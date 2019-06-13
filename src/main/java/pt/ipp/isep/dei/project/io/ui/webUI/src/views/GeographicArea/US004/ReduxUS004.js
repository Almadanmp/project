import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchGAByT} from './ActionsUS004';

class ReduxUS004 extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchAreasByType();
  }

  render() {
    const {listGAByTypes} = this.props;
    return (
      <>
        <label>
          <h5>Listing Geographic Areas by Type:</h5>
          <p></p>
          {listGAByTypes.map(listGAByTypes => (
            <p key={listGAByTypes.id}>{'- ' + listGAByTypes.name} </p>
          ))}
        </label>
        </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    listGAByType: state.ReducerUS004.listGAByType
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchAreasByType: () => {
      dispatch(fetchGAByT())
    }
  }
};

export default connect (
  mapStateToProps,
  mapDispatchToProps
)(ReduxUS004);
